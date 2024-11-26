package com.erbaproger.water_balance_tracker.dto;

import com.erbaproger.water_balance_tracker.entities.WaterRecord;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;


@Getter
@Setter
public class WaterRecordDto {
    @NotNull(message = "Date is required!!!")
    private LocalDate date;

    @Min(value = 0, message = "You can't drink a negative amount!!!")
    private int amount;

    @NotNull(message = "User ID is required!!!")
    private Long userId; // Reference to the associated User


//    private WaterRecordDto mapToWaterRecordDTO(WaterRecord waterRecord) {
//        WaterRecordDto dto = new WaterRecordDto();
//        dto.setUserId(waterRecord.getUser().getId());
//        dto.setDate(waterRecord.getDate());
//        dto.setAmount(waterRecord.getAmount());
//        return dto;
//    }

}
