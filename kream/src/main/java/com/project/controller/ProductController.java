package com.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.model.BuySellDTO;
import com.project.model.ProductDTO;
import com.project.service.ProductService;

@Controller
public class ProductController {
	
	@Autowired private ProductService ps;

	@GetMapping("/products/{idx}")
	public ModelAndView products(@PathVariable("idx") int idx) {
		ModelAndView mav = new ModelAndView("/products/products");
		
		ProductDTO product = ps.getProductInfo(idx);
		mav.addObject("product", product);
		
		String conclusionPrice = ps.getConclusionPrice(idx);
		mav.addObject("conclusionPrice", conclusionPrice);
		
		String buyPrice = ps.getBuyPrice(idx);
		String sellPrice = ps.getSellPrice(idx);
		mav.addObject("buyPrice", sellPrice);
		mav.addObject("sellPrice", buyPrice);
		
		List<ProductDTO> moreProduct = ps.getMoreProduct(product);
		mav.addObject("moreProduct", moreProduct);
		
		HashMap<String, String> buySizeList = ps.BuySizePriceList(product);
		mav.addObject("buySizeList", buySizeList);
		
		HashMap<String, String> sellSizeList = ps.SellSizePriceList(product);
		mav.addObject("sellSizeList", sellSizeList);
		
		ArrayList<String> categorySize = ps.categorySize(product.getCategory());
		mav.addObject("categorySize", categorySize);
		
		ArrayList<BuySellDTO> conclusionList = ps.getConclusionList(idx);
		mav.addObject("conclusionList", conclusionList);
		
		ArrayList<BuySellDTO> sellList = ps.getSellList(idx,"all");
		mav.addObject("sellList", sellList);
		
		ArrayList<BuySellDTO> buyList = ps.getBuyList(idx,"all");
		mav.addObject("buyList", buyList);
		
		ArrayList<String> chartXData = ps.getChartXData(idx,"chartAll");
		mav.addObject("chartXData", chartXData);
		
		ArrayList<String> chartYData = ps.getChartYData(idx,"chartAll");
		mav.addObject("chartYData", chartYData);
		
		return mav;
	}
	
	@GetMapping("/products/{idx}/{size}")
	@ResponseBody
	public ArrayList<String> sizeSelectPrice(@PathVariable int idx, @PathVariable String size) {
		ArrayList<String> priceList = new ArrayList<String>();
		if(size.equals("all") || size.equals("모든 사이즈")) {
			priceList = ps.sizeAllPrice(idx);
		} else {
			priceList = ps.sizeSelectPrice(idx, size);
		}
		return priceList;
	}
	
	@PostMapping("/sizeWishList")
	@ResponseBody
	public ArrayList<String> getSizeWishList(@RequestBody Map<String, String> data) {
		String productIdx = data.get("productIdx");
		String memberIdx = data.get("memberIdx");
		return ps.getSizeWishList(productIdx,memberIdx);
	}
	
	@PostMapping("/sizeWishOff")
	@ResponseBody
	public void sizeWishOff(@RequestBody Map<String, String> data) {
		String productIdx = data.get("productIdx");
		String memberIdx = data.get("memberIdx");
		String size = data.get("size");
		ps.deleteProductWish(productIdx,memberIdx,size);
	}
	
	@PostMapping("/sizeWishOn")
	@ResponseBody
	public void sizeWishOn(@RequestBody Map<String, String> data) {
		String productIdx = data.get("productIdx");
		String memberIdx = data.get("memberIdx");
		String size = data.get("size");
		ps.insertProductWish(productIdx,memberIdx,size);
	}
	
	@GetMapping("/conclusionList/{productIdx}/{size}")
	@ResponseBody
	public ArrayList<BuySellDTO> conclusionList(@PathVariable int productIdx,@PathVariable String size) {
		ArrayList<BuySellDTO> conclusionList = new ArrayList<BuySellDTO>();
		if(size.equals("all") || size.equals("모든 사이즈")) {
			conclusionList = ps.getConclusionList(productIdx);
		} else {
			conclusionList = ps.getConclusionList(productIdx,size);
		}
		return conclusionList;
	}
	
	@GetMapping("/sellList/{productIdx}/{size}")
	@ResponseBody
	public ArrayList<BuySellDTO> sellList(@PathVariable int productIdx, @PathVariable String size) {
		return ps.getSellList(productIdx, size);
	}
	
	@GetMapping("/buyList/{productIdx}/{size}")
	@ResponseBody
	public ArrayList<BuySellDTO> buyList(@PathVariable int productIdx, @PathVariable String size) {
		return ps.getBuyList(productIdx, size);
	}
	
	@GetMapping("/chartData/{productIdx}/{tabId}")
	@ResponseBody
	public ArrayList<ArrayList<String>> chartData(@PathVariable int productIdx,@PathVariable String tabId) {
		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		list.add(ps.getChartXData(productIdx, tabId));
		list.add(ps.getChartYData(productIdx, tabId));
		return list;
	}
	
}
