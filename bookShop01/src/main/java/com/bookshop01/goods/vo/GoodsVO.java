package com.bookshop01.goods.vo;

import java.sql.Date;
import org.springframework.stereotype.Component;

@Component("goodsVO")
public class GoodsVO {
    private int goods_id;
    private String goods_title;
    private int goods_price;
    private int goods_sales_price;
    private int goods_point;
    private String goods_sort;
    private String goods_status;
    private Date goods_creDate;

    // 새로 추가된 썸네일 이미지 컬럼
    private String goods_fileName;

    // JSP 매핑용 별칭 (fileName)
    private String fileName;

    // 상품 소개
    private String goods_intro;

    // 주문 상세 설명 (CLOB 매핑)
    private String goods_contents_order;

    public GoodsVO() {}

    public int getGoods_id() { return goods_id; }
    public void setGoods_id(int goods_id) { this.goods_id = goods_id; }

    public String getGoods_title() { return goods_title; }
    public void setGoods_title(String goods_title) { this.goods_title = goods_title; }

    public int getGoods_price() { return goods_price; }
    public void setGoods_price(int goods_price) { this.goods_price = goods_price; }

    public int getGoods_sales_price() { return goods_sales_price; }
    public void setGoods_sales_price(int goods_sales_price) { this.goods_sales_price = goods_sales_price; }

    public int getGoods_point() { return goods_point; }
    public void setGoods_point(int goods_point) { this.goods_point = goods_point; }

    public String getGoods_sort() { return goods_sort; }
    public void setGoods_sort(String goods_sort) { this.goods_sort = goods_sort; }

    public String getGoods_status() { return goods_status; }
    public void setGoods_status(String goods_status) { this.goods_status = goods_status; }

    public Date getGoods_creDate() { return goods_creDate; }
    public void setGoods_creDate(Date goods_creDate) { this.goods_creDate = goods_creDate; }

    public String getGoods_fileName() { return goods_fileName; }
    public void setGoods_fileName(String goods_fileName) { this.goods_fileName = goods_fileName; }

    // ===== 추가된 getter/setter =====
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getGoods_intro() { return goods_intro; }
    public void setGoods_intro(String goods_intro) { this.goods_intro = goods_intro; }

    public String getGoods_contents_order() { return goods_contents_order; }
    public void setGoods_contents_order(String goods_contents_order) { this.goods_contents_order = goods_contents_order; }
}
