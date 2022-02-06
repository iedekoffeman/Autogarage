package nl.novi.autogarage.service;

import nl.novi.autogarage.dto.ItemRequestDto;
import nl.novi.autogarage.exception.RecordNotFoundException;
import nl.novi.autogarage.model.Item;
import nl.novi.autogarage.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public Item getItem(int id) {
        Optional<Item> optionalItem = itemRepository.findById(id);

        if(optionalItem.isPresent()) {
            return optionalItem.get();
        } else {
            throw new RecordNotFoundException("An Item with ID " + id + " does not exist.");
        }
    }

    public List<Item> getAllItems() {

        return itemRepository.findAll();

    }

    public void deleteItem(int id) {
        itemRepository.deleteById(id);
    }
    public int createItem(ItemRequestDto itemRequestDto) {

        Item item = new Item();
        item.setName(itemRequestDto.getName());
        item.setPrice(itemRequestDto.getPrice());

        Item newItem = itemRepository.save(item);
        return newItem.getId();

    }
    public void updateItem(int id, Item item) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if (!item.getName().isEmpty()) {
            existingItem.setName(item.getName());
        }
        itemRepository.save(existingItem);
    }
    public void partialUpdateItem(int id, Item item) {
        Item existingItem = itemRepository.findById(id).orElse(null);

        if (!(item.getName() == null) && !item.getName().isEmpty()) {
            existingItem.setName(item.getName());
        }

        itemRepository.save(existingItem);
    }

}
