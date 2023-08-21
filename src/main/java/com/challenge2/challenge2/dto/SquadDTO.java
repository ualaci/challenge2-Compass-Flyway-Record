package com.challenge2.challenge2.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Builder
public record SquadDTO (String squadName, List<Long> students){}
