using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Responses
{
    public class UserResponse : User
    {
    }

    public class UserResponseMapperProfile : Profile
    {
        public UserResponseMapperProfile()
        {
            CreateMap<UserEntity, UserResponse>()
                .ForMember(d => d.Password, s => s.Ignore());
        }
    }
}