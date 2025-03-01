package com.example.demo.util;

import com.example.demo.dto.ScheduleDTO;
import com.example.demo.dto.common.ContactInfoDTO;
import com.example.demo.model.embeddables.ContactInfo;
import com.example.demo.model.embeddables.ScheduleInfo;

public class CommonMapper {

    public static ContactInfoDTO contactInfoToDTO(ContactInfo contactInfo) {
        return new ContactInfoDTO(contactInfo.getEmail());
    }

    public static ContactInfo contactInfoToEntity(ContactInfoDTO contactInfoDTO) {
        return new ContactInfo(contactInfoDTO.getEmail(), null);
    }

    public static ScheduleDTO scheduleInfoToDTO(ScheduleInfo scheduleInfo) {
        return new ScheduleDTO(
                scheduleInfo.getSemester(),
                scheduleInfo.getSchedule(),
                scheduleInfo.getLocation());
    }

    public static ScheduleInfo scheduleInfoToEntity(ScheduleDTO scheduleDTO) {
        return new ScheduleInfo(
                scheduleDTO.getSemester(),
                scheduleDTO.getSchedule(),
                scheduleDTO.getLocation());
    }

}
