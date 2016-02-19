package jp.co._3sss.shop;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class OracleItemDao implements ItemDao {
    
    private JdbcTemplate template;
    
    @Autowired
    public void setDataSource(DataSource ds) {
        template = new JdbcTemplate(ds);
    }
    
    private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

    @Override
    public Item find(int id) {
        String sql = "select id, name, price from items where id = ?";
        Object[] args = {id};
        return template.queryForObject(sql, args, ITEM_ROW_MAPPER);
    }

    @Override
    public List<Item> all() {
        String sql = "select id, name, price from items";
        List<Item> list = template.query(sql, ITEM_ROW_MAPPER);
        return list;
    }

    @Override
    public void create(Item item) {
        item.setId(getNewId());
        String sql = "insert into items (id, name, price) values (?, ?, ?)";
        Object[] args = {item.getId(), item.getName(), item.getPrice()};
        template.update(sql, args);
    }

    private int getNewId() {
        String sqlForId = "select item_seq.nextval from dual";
        return template.queryForObject(sqlForId, Integer.class);
    }

    @Override
    public void update(Item item) {
        String sql = "update items set name = ?, price = ? where id = ?";
        Object[] args = {item.getName(), item.getPrice(), item.getId()};
        template.update(sql, args);
    }

    @Override
    public void delete(int id) {
        String sql = "delete from items where id = ?";
        Object[] args = {id};
        template.update(sql, args);
    }
    
}
