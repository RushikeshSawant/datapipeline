package demo.app.services;

import demo.app.dtos.UserDto;

/**
 * Implements {@link IProcessor} to process user data.
 * 
 * @author rushikesh
 *
 */
public class UserDataService implements IProcessor<UserDto> {

	@Override
	public long process(UserDto userDto) {
		return 0l;
	}

}
