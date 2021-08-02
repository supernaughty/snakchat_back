package com.snackchat.controller;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
	private final UserService userService;

	@PostMapping("/create")
	public ResponseEntity<List<MemberDto>> createMember(@RequestBody MemberDto dto)
			throws DuplicateException, DuplicateException {
		List<MemberDto> result = memberService.createMember(dto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
