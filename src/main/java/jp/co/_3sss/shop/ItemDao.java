package jp.co._3sss.shop;

import java.util.List;

public interface ItemDao {
    Item find(int id);
    List<Item> all();
    void create(Item item);
    void update(Item item);
    void delete(int id);
}
