package nl.novi.autogarage.controller;

import nl.novi.autogarage.dto.ItemRequestDto;
import nl.novi.autogarage.model.Item;
import nl.novi.autogarage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping(value =  "/items")
    public ResponseEntity<Object> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }

    @GetMapping(value = "/items/{id}")
    public ResponseEntity<Object> getItem(@PathVariable int id) {
        return ResponseEntity.ok(itemService.getItem(id));
    }

    @DeleteMapping(value = "/items/{id}")
    public ResponseEntity<Object> deleteItem(@PathVariable int id) {

        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/items")
    public ResponseEntity<Object> addItem(@Valid @RequestBody ItemRequestDto itemRequestDto) {

        int newId = itemService.addItem(itemRequestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).build();

    }

    @PutMapping(value = "/items/{id}")
    public ResponseEntity<Object> updateItem(@PathVariable int id, @RequestBody Item item) {

        itemService.updateItem(id, item);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/items/{id}")
    public ResponseEntity<Object> partialupdateItem(@PathVariable int id, @RequestBody Item item) {

        itemService.partialUpdateItem(id, item);
        return ResponseEntity.noContent().build();
    }

}
