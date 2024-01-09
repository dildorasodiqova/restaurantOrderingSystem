package uz.cosinus.restaurantorderingsystem.service.floorService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.FloorCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FloorResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.FloorEntity;
import uz.cosinus.restaurantorderingsystem.exception.DataAlreadyExistsException;
import uz.cosinus.restaurantorderingsystem.exception.DataNotFoundException;
import uz.cosinus.restaurantorderingsystem.repository.FloorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FloorServiceImpl implements FloorService{
    private final FloorRepository floorRepository;
    @Override
    public String create(FloorCreateDto dto) {
        FloorEntity entity = parse(dto);
        if (floorRepository.existsAllByNumber(dto.getNumber())){
            throw new DataAlreadyExistsException("This floor number already exists. Please choose another number");
        }
        FloorEntity save = floorRepository.save(entity);
        return "Successfully";
    }

    @Override
    public List<FloorResponseDto> getAll() {
        List<FloorEntity> all = floorRepository.findAllByIsActiveTrue();
        return parse(all);
    }

    @Override
    public String disActive(Integer floorNumber) {
        FloorEntity entity = floorRepository.findByNumber(floorNumber).orElseThrow(() -> new DataNotFoundException("Floor not found"));

    }

    @Override
    public FloorEntity findById(UUID floorId) {
        return floorRepository.findById(floorId).orElseThrow(() -> new DataNotFoundException("Floor not found"));
    }

    private List<FloorResponseDto> parse(List<FloorEntity> all){
        List<FloorResponseDto> list = new ArrayList<>();
        for (FloorEntity entity : all) {
            list.add(new FloorResponseDto(entity.getId(), entity.getNumber(), entity.getDescription(), entity.getCreatedDate()));
        }
        return list;
    }
    private FloorEntity parse(FloorCreateDto dto){
        return new FloorEntity(dto.getNumber(), dto.getDescription());
    }
}
