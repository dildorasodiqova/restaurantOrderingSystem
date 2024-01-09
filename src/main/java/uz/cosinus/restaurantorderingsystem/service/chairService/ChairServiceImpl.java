package uz.cosinus.restaurantorderingsystem.service.chairService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.ChairCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.ChairResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.ChairEntity;
import uz.cosinus.restaurantorderingsystem.entities.TableEntity;
import uz.cosinus.restaurantorderingsystem.repository.ChairRepository;
import uz.cosinus.restaurantorderingsystem.service.tableService.TableService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChairServiceImpl implements ChairService{
    private  final ChairRepository chairRepository;
    private final TableService tableService;
    @Override
    public String create(ChairCreateDto dto) {
        for (int i = 0; i < dto.getCount(); i++) {
            TableEntity table = tableService.finById(dto.getTableId());
            chairRepository.save(new ChairEntity(table));
        }
        return "Successfully";
    }

    @Override
    public List<ChairResponseDto> chairsOfTable(UUID tableId) {
        List<ChairEntity> chairs = chairRepository.findAllByTableIdAndIsActiveTrue(tableId);
        return parse(chairs);
    }

    private List<ChairResponseDto> parse(List<ChairEntity> chairs){
        List<ChairResponseDto> list = new ArrayList<>();
        for (ChairEntity chair : chairs) {
            list.add(new ChairResponseDto(chair.getId(), chair.getTable().getId(), chair.getTable().getTableNumber(), chair.getCreatedDate()));
        }
        return list;
    }

}
