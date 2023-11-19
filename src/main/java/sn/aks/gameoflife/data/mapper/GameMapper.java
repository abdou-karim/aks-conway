package sn.aks.gameoflife.data.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import sn.aks.gameoflife.data.model.GameModel;
import sn.aks.gameoflife.web.dto.Request.GameCreateRequestDto;
import sn.aks.gameoflife.web.dto.Response.GameResponseDto;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = MappingConstants.ComponentModel.SPRING)
public interface GameMapper {
  GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

  GameModel toEntity(GameCreateRequestDto requestDto);

  GameResponseDto toDto(GameModel model);

  List<GameResponseDto> toDtos(List<GameModel> models);
}
