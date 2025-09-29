package com.bookshop01.admin.goods.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bookshop01.admin.goods.service.AdminGoodsService;
import com.bookshop01.common.base.BaseController;
import com.bookshop01.goods.vo.GoodsVO;
import com.bookshop01.goods.vo.ImageFileVO;
import com.bookshop01.member.vo.MemberVO;

@Controller("adminGoodsController")
@RequestMapping(value = "/admin/goods")
public class AdminGoodsControllerImpl extends BaseController implements AdminGoodsController {

    // 로컬 개발 시: "C:\\shopping\\file_repo"
    // Cafe24 서버 배포 시: "/home/hosting_users/kwun167/uploads/goods"
    private static final String CURR_IMAGE_REPO_PATH = "/home/hosting_users/kwun167/uploads/goods";

    // JSP 접근용 URL prefix
    private static final String IMAGE_URL_PREFIX = "/uploads/goods/";

    // ===== 디렉터리 보장 헬퍼 =====
    private void ensureRepoDirs() {
        File repo = new File(CURR_IMAGE_REPO_PATH);
        if (!repo.exists())
            repo.mkdirs();
        File temp = new File(repo, "temp");
        if (!temp.exists())
            temp.mkdirs();
    }

    // ===== 숫자만 남기는 헬퍼 =====
    private String digits(Object v) {
        if (v == null) return "0";
        String s = String.valueOf(v).replaceAll("[^0-9]", "");
        return s.isEmpty() ? "0" : s;
    }

    @Autowired
    private AdminGoodsService adminGoodsService;

    // ✅ 메인 화면
    @RequestMapping(value = "/adminGoodsMain.do", method = { RequestMethod.POST, RequestMethod.GET })
    public ModelAndView adminGoodsMain(@RequestParam Map<String, String> dateMap,
                                       HttpServletRequest request,
                                       HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        HttpSession session = request.getSession();
        session.setAttribute("side_menu", "admin_mode");

        String fixedSearchPeriod = dateMap.get("fixedSearchPeriod");
        String section = dateMap.get("section");
        String pageNum = dateMap.get("pageNum");

        String[] tempDate = calcSearchPeriod(fixedSearchPeriod).split(",");
        String beginDate = tempDate[0];
        String endDate = tempDate[1];
        dateMap.put("beginDate", beginDate);
        dateMap.put("endDate", endDate);

        Map<String, Object> condMap = new HashMap<>();
        condMap.put("section", section == null ? "1" : section);
        condMap.put("pageNum", pageNum == null ? "1" : pageNum);
        condMap.put("beginDate", beginDate);
        condMap.put("endDate", endDate);

        List<GoodsVO> newGoodsList = adminGoodsService.listNewGoods(condMap);
        mav.addObject("newGoodsList", newGoodsList);

        mav.addObject("beginYear", beginDate.split("-")[0]);
        mav.addObject("beginMonth", beginDate.split("-")[1]);
        mav.addObject("beginDay", beginDate.split("-")[2]);
        mav.addObject("endYear", endDate.split("-")[0]);
        mav.addObject("endMonth", endDate.split("-")[1]);
        mav.addObject("endDay", endDate.split("-")[2]);

        mav.addObject("section", condMap.get("section"));
        mav.addObject("pageNum", condMap.get("pageNum"));
        return mav;
    }

    // ✅ 새 상품 추가
    @RequestMapping(value = "/addNewGoods.do", method = { RequestMethod.POST })
    public ResponseEntity addNewGoods(MultipartHttpServletRequest multipartRequest,
                                      HttpServletResponse response) throws Exception {
        ensureRepoDirs();
        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        String imageFileName = null;

        Map newGoodsMap = new HashMap();
        Enumeration enu = multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String value = multipartRequest.getParameter(name);
            newGoodsMap.put(name, value);
        }

        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String reg_id = (memberVO != null) ? memberVO.getMember_id() : "admin";

        List<ImageFileVO> imageFileList = upload(multipartRequest);
        if (imageFileList == null) imageFileList = new ArrayList();
        if (!imageFileList.isEmpty()) {
            for (ImageFileVO imageFileVO : imageFileList) {
                imageFileVO.setReg_id(reg_id);
                imageFileVO.setFileUrl(IMAGE_URL_PREFIX + imageFileVO.getFileName());
            }
            newGoodsMap.put("imageFileList", imageFileList);
        }

        newGoodsMap.put("goods_price", digits(newGoodsMap.get("goods_price")));
        newGoodsMap.put("goods_sales_price", digits(newGoodsMap.get("goods_sales_price")));
        newGoodsMap.put("goods_point", digits(newGoodsMap.get("goods_point")));

        String message;
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("Content-Type", "text/html; charset=utf-8");

        try {
            int goods_id = adminGoodsService.addNewGoods(newGoodsMap);
            if (!imageFileList.isEmpty()) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    File srcFile = new File(CURR_IMAGE_REPO_PATH + File.separator + "temp" + File.separator + imageFileName);
                    File destDir = new File(CURR_IMAGE_REPO_PATH + File.separator + goods_id);
                    FileUtils.moveFileToDirectory(srcFile, destDir, true);
                }
            }
            message = "<script>alert('새상품을 추가했습니다.');location.href='" 
                    + multipartRequest.getContextPath() + "/admin/goods/addNewGoodsForm.do';</script>";
        } catch (Exception e) {
            if (imageFileList != null && !imageFileList.isEmpty()) {
                for (ImageFileVO imageFileVO : imageFileList) {
                    imageFileName = imageFileVO.getFileName();
                    new File(CURR_IMAGE_REPO_PATH + File.separator + "temp" + File.separator + imageFileName).delete();
                }
            }
            message = "<script>alert('오류가 발생했습니다. 다시 시도해 주세요');location.href='" 
                    + multipartRequest.getContextPath() + "/admin/goods/addNewGoodsForm.do';</script>";
            e.printStackTrace();
        }
        return new ResponseEntity(message, responseHeaders, HttpStatus.OK);
    }

    // ✅ 상품 수정 폼
    @RequestMapping(value = "/modifyGoodsForm.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView modifyGoodsForm(@RequestParam("goods_id") int goods_id,
                                        HttpServletRequest request,
                                        HttpServletResponse response) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        ModelAndView mav = new ModelAndView(viewName);
        Map goodsMap = adminGoodsService.goodsDetail(goods_id);
        mav.addObject("goodsMap", goodsMap);
        return mav;
    }

    // ✅ 상품 정보 수정
    @RequestMapping(value = "/modifyGoodsInfo.do", method = { RequestMethod.POST })
    public ResponseEntity modifyGoodsInfo(@RequestParam("goods_id") String goods_id,
                                          @RequestParam("attribute") String attribute,
                                          @RequestParam("value") String value,
                                          HttpServletRequest request,
                                          HttpServletResponse response) throws Exception {
        if ("goods_price".equals(attribute) || "goods_sales_price".equals(attribute) || "goods_point".equals(attribute)) {
            value = digits(value);
        }

        Map<String, String> goodsMap = new HashMap<>();
        goodsMap.put("goods_id", goods_id);
        goodsMap.put(attribute, value);
        adminGoodsService.modifyGoodsInfo(goodsMap);

        return new ResponseEntity("mod_success", new HttpHeaders(), HttpStatus.OK);
    }

    // ✅ 상품 이미지 수정
    @RequestMapping(value = "/modifyGoodsImageInfo.do", method = { RequestMethod.POST })
    public void modifyGoodsImageInfo(MultipartHttpServletRequest multipartRequest,
                                     HttpServletResponse response) throws Exception {
        ensureRepoDirs();
        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String imageFileName = null;

        Map goodsMap = new HashMap();
        Enumeration enu = multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String value = multipartRequest.getParameter(name);
            goodsMap.put(name, value);
        }

        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String reg_id = (memberVO != null) ? memberVO.getMember_id() : "admin";

        List<ImageFileVO> imageFileList = upload(multipartRequest);
        if (imageFileList != null && !imageFileList.isEmpty()) {
            int goods_id = Integer.parseInt((String) goodsMap.get("goods_id"));
            int image_id = Integer.parseInt((String) goodsMap.get("image_id"));
            for (ImageFileVO imageFileVO : imageFileList) {
                imageFileVO.setGoods_id(goods_id);
                imageFileVO.setImage_id(image_id);
                imageFileVO.setReg_id(reg_id);
                imageFileVO.setFileUrl(IMAGE_URL_PREFIX + imageFileVO.getFileName());
            }
            adminGoodsService.modifyGoodsImage(imageFileList);
            for (ImageFileVO imageFileVO : imageFileList) {
                imageFileName = imageFileVO.getFileName();
                File srcFile = new File(CURR_IMAGE_REPO_PATH + File.separator + "temp" + File.separator + imageFileName);
                File destDir = new File(CURR_IMAGE_REPO_PATH + File.separator + goods_id);
                FileUtils.moveFileToDirectory(srcFile, destDir, true);
            }
        }
    }

    // ✅ 새 이미지 추가
    @Override
    @RequestMapping(value = "/addNewGoodsImage.do", method = { RequestMethod.POST })
    public void addNewGoodsImage(MultipartHttpServletRequest multipartRequest,
                                 HttpServletResponse response) throws Exception {
        ensureRepoDirs();
        multipartRequest.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        String imageFileName = null;

        Map goodsMap = new HashMap();
        Enumeration enu = multipartRequest.getParameterNames();
        while (enu.hasMoreElements()) {
            String name = (String) enu.nextElement();
            String value = multipartRequest.getParameter(name);
            goodsMap.put(name, value);
        }

        HttpSession session = multipartRequest.getSession();
        MemberVO memberVO = (MemberVO) session.getAttribute("memberInfo");
        String reg_id = (memberVO != null) ? memberVO.getMember_id() : "admin";

        List<ImageFileVO> imageFileList = upload(multipartRequest);
        if (imageFileList != null && !imageFileList.isEmpty()) {
            int goods_id = Integer.parseInt((String) goodsMap.get("goods_id"));
            for (ImageFileVO imageFileVO : imageFileList) {
                imageFileVO.setGoods_id(goods_id);
                imageFileVO.setReg_id(reg_id);
                imageFileVO.setFileUrl(IMAGE_URL_PREFIX + imageFileVO.getFileName());
            }
            adminGoodsService.addNewGoodsImage(imageFileList);
            for (ImageFileVO imageFileVO : imageFileList) {
                imageFileName = imageFileVO.getFileName();
                File srcFile = new File(CURR_IMAGE_REPO_PATH + File.separator + "temp" + File.separator + imageFileName);
                File destDir = new File(CURR_IMAGE_REPO_PATH + File.separator + goods_id);
                FileUtils.moveFileToDirectory(srcFile, destDir, true);
            }
        }
    }

    // ✅ 이미지 삭제
    @Override
    @RequestMapping(value = "/removeGoodsImage.do", method = { RequestMethod.POST })
    public void removeGoodsImage(@RequestParam("goods_id") int goods_id,
                                 @RequestParam("image_id") int image_id,
                                 @RequestParam("imageFileName") String imageFileName,
                                 HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        adminGoodsService.removeGoodsImage(image_id);
        File srcFile = new File(CURR_IMAGE_REPO_PATH + File.separator + goods_id + File.separator + imageFileName);
        if (srcFile.exists()) srcFile.delete();
    }

    // ===== 신규 기능 추가 =====

    // ✅ 상품 삭제
    @RequestMapping(value = "/removeGoods.do", method = { RequestMethod.POST })
    public ResponseEntity removeGoods(@RequestParam("goods_id") int goods_id) throws Exception {
        adminGoodsService.removeGoods(goods_id);
        return new ResponseEntity("delete_success", new HttpHeaders(), HttpStatus.OK);
    }

    // ✅ 상품 검색
    @RequestMapping(value = "/searchGoods.do", method = { RequestMethod.GET, RequestMethod.POST })
    public ModelAndView searchGoods(@RequestParam Map<String,Object> searchMap,
                                    HttpServletRequest request) throws Exception {
        String viewName = (String) request.getAttribute("viewName");
        List<GoodsVO> goodsList = adminGoodsService.searchGoods(searchMap);
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("goodsList", goodsList);
        return mav;
    }

    // ✅ 상품 상태 변경
    @RequestMapping(value = "/changeGoodsStatus.do", method = { RequestMethod.POST })
    public ResponseEntity changeGoodsStatus(@RequestParam("goods_id") int goods_id,
                                            @RequestParam("status") String status) throws Exception {
        Map<String,Object> statusMap = new HashMap<>();
        statusMap.put("goods_id", goods_id);
        statusMap.put("goods_status", status);
        adminGoodsService.changeGoodsStatus(statusMap);
        return new ResponseEntity("status_updated", new HttpHeaders(), HttpStatus.OK);
    }
}
