package uz.cosinus.restaurantorderingsystem.service.tableService;

import uz.cosinus.restaurantorderingsystem.dto.createDto.TableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.TableResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.TableEntity;

import java.util.List;
import java.util.UUID;

public interface TableService {
    String create(UUID floorId, TableCreateDto createDto);
    List<TableResponseDto> getAll(int page, int size, UUID floorId);
    TableEntity finById(UUID tableId);
    TableResponseDto getById(UUID tableId);
    String disActive(UUID tableId);
    String active(UUID tableId);


}
