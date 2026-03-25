package com.example.DACK.controller;

import com.example.DACK.model.Field;
import com.example.DACK.service.FieldService;
import com.example.DACK.service.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequiredArgsConstructor
public class FieldController {
    private final FieldService fieldService;
    private final FileStorageService fileStorageService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(required = false) String search) {
        if (search != null && !search.isEmpty()) {
            model.addAttribute("fields", fieldService.searchFields(search));
        } else {
            model.addAttribute("fields", fieldService.getAllFields());
        }
        return "index";
    }

    @GetMapping("/fields/{id}")
    public String fieldDetails(@PathVariable Long id, Model model) {
        model.addAttribute("field", fieldService.getFieldById(id).orElse(null));
        return "field_detail";
    }

    // Admin routes
    @GetMapping("/admin/fields")
    public String adminFields(Model model) {
        model.addAttribute("fields", fieldService.getAllFields());
        return "admin/fields";
    }

    @GetMapping("/admin/fields/add")
    public String addFieldForm(Model model) {
        model.addAttribute("field", new Field());
        return "admin/field_form";
    }

    @PostMapping("/admin/fields/save")
    public String saveField(@ModelAttribute Field field, @RequestParam("imageFile") MultipartFile imageFile) {
        if (!imageFile.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(imageFile);
            field.setImageUrl(imageUrl);
        }
        fieldService.saveField(field);
        return "redirect:/admin/fields";
    }

    @GetMapping("/admin/fields/edit/{id}")
    public String editFieldForm(@PathVariable Long id, Model model) {
        model.addAttribute("field", fieldService.getFieldById(id).orElse(null));
        return "admin/field_form";
    }

    @GetMapping("/admin/fields/delete/{id}")
    public String deleteField(@PathVariable Long id) {
        fieldService.deleteField(id);
        return "redirect:/admin/fields";
    }
}
