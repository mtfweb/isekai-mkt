package com.bookshop01.goods.vo;

import org.springframework.stereotype.Component;

@Component("goodsVO")
public class GoodsVO {
	
    private int goods_id;                // 상품번호
    private String goods_title;          // 상품이름
    private int goods_sales_price;       // 상품 가격
    private String goods_creDate;        // 입고일자 (SQL alias 매핑)

    // ===== Getter/Setter =====
    public int getGoods_id() {
        return goods_id;
    }
    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public String getGoods_title() {
        return goods_title;
    }
    public void setGoods_title(String goods_title) {
        this.goods_title = goods_title;
    }

    public int getGoods_sales_price() {
        return goods_sales_price;
    }
    public void setGoods_sales_price(int goods_sales_price) {
        this.goods_sales_price = goods_sales_price;
    }

    public String getGoods_creDate() {
        return goods_creDate;
    }
    public void setGoods_creDate(String goods_creDate) {
        this.goods_creDate = goods_creDate;
    }

}
