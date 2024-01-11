package uz.cosinus.restaurantorderingsystem.service.foodService;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.FoodCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.FoodResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.FoodEntity;
import uz.cosinus.restaurantorderingsystem.exception.DataAlreadyExistsException;
import uz.cosinus.restaurantorderingsystem.exception.DataNotFoundException;
import uz.cosinus.restaurantorderingsystem.repository.FoodRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {
    private final FoodRepository foodRepository;
    private final ModelMapper modelMapper;

    @Override
    public String create(FoodCreateDto dto) {
        boolean b = foodRepository.existsAllByName(dto.getName());
        if (b) {
            throw new DataAlreadyExistsException("This food name already exists");
        }
        FoodEntity parse = parse(dto);
        foodRepository.save(parse);
        return "Successfully";
    }

    @Override
    public String disActiveOrActive(UUID foodId, Boolean trueOrFalse) {
        int i = foodRepository.disActiveOrActive(foodId, trueOrFalse);
        if (i == 0) {
            throw new DataNotFoundException("Food not found");
        }
        return "Successfully";
    }

    @Override
    public List<FoodResponseDto> getAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        List<FoodEntity> all = foodRepository.getAllByIsActiveTrue(pageRequest);
        return parse(all);
    }

    @Override
    public String update(FoodCreateDto dto, UUID foodId) {
        boolean foodExistsWithGivenNameAndId = foodRepository.existsAllByNameAndId(dto.getName(), foodId);

        if (foodExistsWithGivenNameAndId) {
            int updateResult = foodRepository.updateFood(dto.getName(), dto.getDescription(), dto.getPrice(), foodId);

            if (updateResult == 0) {
                throw new DataNotFoundException("Food not found");
            }
            return "Successfully";
        } else if (foodRepository.existsAllByName(dto.getName())) {
            return "This food name already exists. Please choose another name";
        } else {
            int updateResult = foodRepository.updateFood(dto.getName(), dto.getDescription(), dto.getPrice(), foodId);

            if (updateResult == 0) {
                throw new DataNotFoundException("Food not found");
            }

            return "Successfully";
        }
    }

    private FoodEntity parse(FoodCreateDto dto) {
        return modelMapper.map(dto, FoodEntity.class);
    }


    public List<FoodResponseDto> parse(List<FoodEntity> all) {
        List<FoodResponseDto> list = new ArrayList<>();
        for (FoodEntity food : all) {
            list.add(modelMapper.map(food, FoodResponseDto.class));
        }
        return list;
    }

    @Override
    public FoodResponseDto getById(UUID foodId) {
        FoodEntity food = foodRepository.findById(foodId).orElseThrow(() -> new DataNotFoundException("Food not found"));
        return new FoodResponseDto(food.getId(), food.getName(), food.getPrice(), food.getDescription(), food.getTimeToGetReady(), food.getIsActive(), food.getCreatedDate(), food.getDiscountPercentage());
    }

    @Override
    public String updateDiscountPercentage(UUID foodId, Double percentage) {
        int i = foodRepository.updateDiscountPercentage(foodId, percentage);
        if (i == 0){
            throw new DataNotFoundException("food not found");
        }
        return "Update discount percentage .";
    }
}
