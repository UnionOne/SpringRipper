package com.github.union.controller;

import com.github.union.exception.ShopNotFoundException;
import com.github.union.model.Shop;
import com.github.union.service.ShopService;
import com.github.union.validation.ShopValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @Autowired
    private ShopValidator shopValidator;

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.setValidator(shopValidator);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newShopPage() {
        return new ModelAndView("shop-new", "shop", new Shop());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewShop(@ModelAttribute @Valid Shop shop,
                                      BindingResult result,
                                      final RedirectAttributes attributes) {
        if (result.hasErrors()) {
            return new ModelAndView("shop-new");
        }

        ModelAndView view = new ModelAndView();
        String message = "New shop " + shop.getName() + " was created";

        shopService.create(shop);
        view.setViewName("redirect:/index.html");

        attributes.addFlashAttribute("message", message);
        return view;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView shopListPage() {
        ModelAndView view = new ModelAndView("shop-list");
        List<Shop> shopList = shopService.findAll();
        view.addObject("shopList", shopList);
        return view;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editShopPage(@PathVariable Integer id) {
        ModelAndView view = new ModelAndView("shop-edit");
        Shop shop = shopService.findById(id);
        view.addObject("shop", shop);
        return view;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
    public ModelAndView editShop(@ModelAttribute @Valid Shop shop,
                                 BindingResult result, @PathVariable Integer id,
                                 final RedirectAttributes attributes) throws ShopNotFoundException {

        if(result.hasErrors()) {
            return new ModelAndView("shop-edit");
        }

        ModelAndView view = new ModelAndView("redirect:/index.html");
        String message = "Shop was successfully updated.";

        shopService.update(shop);

        attributes.addFlashAttribute("message", message);
        return view;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteShop(@PathVariable Integer id,
                                   final RedirectAttributes attributes) throws ShopNotFoundException {

        ModelAndView view = new ModelAndView("redirect:/index.html");

        Shop shop = shopService.delete(id);
        String message = "The shop " + shop.getName() + " was deleted";

        attributes.addFlashAttribute("message", message);
        return view;
    }
}