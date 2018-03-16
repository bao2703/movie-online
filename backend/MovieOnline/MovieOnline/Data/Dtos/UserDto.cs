using AutoMapper;
using MovieOnline.Data.Domains;

namespace MovieOnline.Data.Dtos
{
    public class UserDto
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public string Avatar { get; set; }

        public string Email { get; set; }

        public string Password { get; set; }

        public Role Role { get; set; }
    }

    public class UserDtoMapperProfile : Profile
    {
        public UserDtoMapperProfile()
        {
            CreateMap<User, UserDto>()
                .ForMember(d => d.Password, s => s.Ignore());
        }
    }
}