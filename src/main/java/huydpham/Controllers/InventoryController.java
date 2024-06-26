package huydpham.Controllers;

import huydpham.Model.Guitar;

import huydpham.Repository.InventoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/guitars")
public class InventoryController {
    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @PostMapping("/add")
    public boolean add(@RequestBody Guitar data) {
        try {
            return InventoryRepository.addGuitar(data);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/get/{serialNumber}")
    public ResponseEntity<Guitar> getGuitar(@PathVariable String serialNumber) {
        try {
            System.out.println("Serial Number: " + serialNumber);
            Guitar guitar = InventoryRepository.getGuitar(serialNumber);
            if (guitar != null) {
                return ResponseEntity.ok(guitar);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/search")
    public List<Guitar> searchGuitars(
            @RequestParam(required = false) String serialNumber,
            @RequestParam(required = false) Double price,
            @RequestParam(required = false) String builder,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String backWood,
            @RequestParam(required = false) String topWood) {
        try {
            System.out.println("Serial Number: " + serialNumber);
            System.out.println("Price: " + price);
            System.out.println("Builder: " + builder);
            System.out.println("Model: " + model);
            System.out.println("Type: " + type);
            System.out.println("Back Wood: " + backWood);
            System.out.println("Top Wood: " + topWood);

            return InventoryRepository.search(serialNumber, price, builder, model, type, backWood, topWood);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
