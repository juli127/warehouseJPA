package com.gmail.kramarenko104.warehouseJPA.entity;

import javax.persistence.*;

@Entity
@Table(name = "warehouse")
public class WareHouse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long prod_id;

    private int amount;

    public WareHouse(){}

    @Column(name="id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="prod_id")
    public long getProduct_id() {
        return prod_id;
    }

    public void setProduct_id(long product_id) {
        this.prod_id = prod_id;
    }

    @Column(name="amount")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }



//    HashMap<Product, Integer> goods;
//
//   public WareHouse(HashMap<Product, Integer> goods) {
//       this.goods = goods;
//  }
//
//
//    public void setGoods(HashMap<Product, Integer> goods) {
//        this.goods = goods;
//    }
//
//    public HashMap<Product, Integer> getGoods() {
//        return goods;
//    }
//
//    public void addProduct(Product product, Integer count) {
//        if (goods.containsKey(product)){
//            goods.put(product, goods.get(product) + count);
//        }
//        else{
//            goods.put(product, count);
//        }
//    }
//
//    public boolean removeSoldProduct(Product product, Integer soldCount) {
//        if (goods.containsKey(product)){
//            if(goods.get(product) >= soldCount){
//                goods.replace(product, goods.get(product) - soldCount);
//                return true;
//            }
//        }
//        return false;
//    }

//    public int getProductCount(Product product){
//        if (goods.containsKey(product)){
//            return goods.get(product);
//        }
//        else {
//            return 0;
//        }
//    }


}
