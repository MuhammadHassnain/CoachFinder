package com.coachfinder.coaches.mapper;

import com.coachfinder.coaches.dto.AreaDTO;
import com.coachfinder.coaches.entity.CoachArea;

public class AreaMapper {
	
	public static AreaDTO AreaToAreaDto(CoachArea area) {
		AreaDTO dto = new AreaDTO();
		dto.setId(area.getId());
		dto.setName(area.getName());
		return dto;
	}
	
	
	public static CoachArea AreaDTOtoArea(AreaDTO areaDTO) {
		CoachArea area = new CoachArea(areaDTO.getName());
		return area;
	}
}
