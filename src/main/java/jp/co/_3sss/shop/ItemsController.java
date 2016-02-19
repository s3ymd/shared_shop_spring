package jp.co._3sss.shop;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ItemsController {
    
    private ItemDao itemDao;
    
    @Autowired
    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
    
    @RequestMapping("/items")
    public String index(Model model) {
        List<Item> items = itemDao.all();
        model.addAttribute("items", items);
        return "items/index";        
    }
    
    @RequestMapping("/items/{id}")
    public String show(@PathVariable int id, Model model) {
        model.addAttribute("item", itemDao.find(id));
        return "items/show";        
    }
    
    @RequestMapping("/items/new")
    public String newItem() {
        return "items/new";        
    }
    
    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public String create(ItemForm form) {
        Item item = new Item();
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        itemDao.create(item);
        
        return "redirect:/items/" + item.getId();
    }
    
    @RequestMapping("/items/{id}/edit")
    public String edit(@PathVariable int id, Model model) {
        model.addAttribute("item", itemDao.find(id));
        return "items/edit";        
    }
    
    @RequestMapping(path = "/items/{id}/update", method = RequestMethod.POST)
    public String update(@PathVariable int id, ItemForm form) {
        Item item = itemDao.find(id);
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        itemDao.update(item);
        return "redirect:/items/" + item.getId();
    }
    
    @RequestMapping("/items/{id}/delete")
    public String delete(@PathVariable int id) {
        itemDao.delete(id);
        return "redirect:/items";        
    }

}
