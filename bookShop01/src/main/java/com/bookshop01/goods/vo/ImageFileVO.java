package com.bookshop01.goods.vo;

import org.springframework.stereotype.Component;

@Component("imageFileVO")
public class ImageFileVO {
    private int image_id;      // 이미지 PK
    private int goods_id;      // 상품 ID
    private String fileName;   // 실제 파일명
    private String fileType;   // main_image / detail_image
    private String reg_id;     // 등록자 ID

    public ImageFileVO() {}

    public int getImage_id() {
        return image_id;
    }
    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public int getGoods_id() {
        return goods_id;
    }
    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getReg_id() {
        return reg_id;
    }
    public void setReg_id(String reg_id) {
        this.reg_id = reg_id;
    }
}
