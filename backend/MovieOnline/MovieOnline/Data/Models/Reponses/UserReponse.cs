using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Reponses
{
    public class UserReponse : User
    {
    }

    public class UserReponseMapperProfile : Profile
    {
        public UserReponseMapperProfile()
        {
            CreateMap<UserEntity, UserReponse>()
                .ForMember(d => d.Password, s => s.Ignore());
        }
    }
}