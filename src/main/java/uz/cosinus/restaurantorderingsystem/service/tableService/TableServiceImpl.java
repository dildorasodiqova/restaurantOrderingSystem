package uz.cosinus.restaurantorderingsystem.service.tableService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.cosinus.restaurantorderingsystem.dto.createDto.TableCreateDto;
import uz.cosinus.restaurantorderingsystem.dto.responseDto.TableResponseDto;
import uz.cosinus.restaurantorderingsystem.entities.FloorEntity;
import uz.cosinus.restaurantorderingsystem.entities.TableEntity;
import uz.cosinus.restaurantorderingsystem.exception.BadRequestException;
import uz.cosinus.restaurantorderingsystem.exception.DataNotFoundException;
import uz.cosinus.restaurantorderingsystem.repository.OrderTableRepository;
import uz.cosinus.restaurantorderingsystem.repository.TableRepository;
import uz.cosinus.restaurantorderingsystem.service.floorService.FloorService;
import uz.cosinus.restaurantorderingsystem.service.orderTableService.OrderTableService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class TableServiceImpl implements TableService{
    private final FloorService floorService;
    private final TableRepository tableRepository;
    private final OrderTableRepository orderTableRepository;
    @Override
    public String create(UUID floorId, TableCreateDto createDto) {
        FloorEntity byId = floorService.findById(floorId);
        for (Integer i = 0; i < createDto.getCount(); i++) { //  shuyerda tekshirish kerakmasmi 0  ga teng emasmi deb
            int countOfTable = tableRepository.countAllBy();
            if(createDto.getCountOfChair() <= 4) {
                tableRepository.save(new TableEntity(byId, countOfTable + 1, 4)); // yani defoult 4 talik stole bo'ladi.
            }
            tableRepository.save(new TableEntity(byId, countOfTable + 1, createDto.getCountOfChair()));
        }
        return "Successfully";
    }

    @Override
    public List<TableResponseDto> getAll(int page, int size,UUID floorId ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TableEntity> tables = tableRepository.findAllByFloor_IdAndIsActiveTrue( floorId, pageRequest);
        return parse(tables.getContent());
    }

    @Override
    public TableEntity finById(UUID tableId) {
        return tableRepository.findById(tableId).orElseThrow(() -> new DataNotFoundException("Table not found"));
    }

    @Override
    public TableResponseDto getById(UUID tableId) {
        TableEntity table = tableRepository.findById(tableId).orElseThrow(() -> new DataNotFoundException("Table not found"));
        return parse(table);
    }

    @Override
    public String disActive(UUID tableId) {
        TableEntity table = tableRepository.findById(tableId).orElseThrow(() -> new DataNotFoundException("Table not found"));
        boolean orderOfTable = orderTableRepository.existsAllByTableIdAndIsActiveTrue(table.getId());
        if (orderOfTable){
            throw new BadRequestException("Zakas available for this table, please try again later.");
        }
        table.setIsActive(false);
        tableRepository.save(table);
        return "Deleted the table";
    }

    @Override
    public String active(UUID tableId) {
        TableEntity table = tableRepository.findById(tableId).orElseThrow(() -> new DataNotFoundException("Table not found"));
         table.setIsActive(true);
         tableRepository.save(table);
         return "Active the table";
    }

    @Override
    public List<TableResponseDto> getFreeTable(LocalDateTime startDate, LocalDateTime endDate, int page, int size, UUID floorId) {
        List<TableResponseDto> list = new ArrayList<>();
        return null;
 //// buyerini qanday qilish kerak masalan men 12 dan 8 gacha bo'lgan o'rindiqlarni ko'rmoqchiman. agar uni shuni yarmida bosh va yarmida band bo'ladigan bo'lsa osha stolni band deyishim kkmi bo'shmi
    }



    private List<TableResponseDto> parse(List<TableEntity> tables){
        List<TableResponseDto> list = new ArrayList<>();
        for (TableEntity table : tables) {
            list.add(new TableResponseDto(table.getId(), table.getFloor().getNumber(), table.getTableNumber(), table.getCountOfChair(), table.getCreatedDate()));
        }
        return list;
    }

    private TableResponseDto parse(TableEntity table){
        return new TableResponseDto(table.getId(), table.getFloor().getNumber(), table.getTableNumber(), table.getCountOfChair(), table.getCreatedDate());
    }


}
