package com.snackchat.model.dto.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionDto {
		private String sessionId;
		private String memberUid;
		private String name;
}
