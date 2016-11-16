package demo.app.dtos;

import demo.app.model.User;

/**
 * Builder to construct {@link UserDto} back from model data
 * @author rushikesh
 *
 */
public class UserBuilder {

    private UserBuilder(){
        // do not want its instances
    }
    
    public static UserDto buildUserDto(User user) {
        return new UserDto(user.getEmail(), user.getName());
    }
}
