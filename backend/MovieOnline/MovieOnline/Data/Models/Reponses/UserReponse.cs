using AutoMapper;
using MovieOnline.Data.Domains;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Reponses
{
    public class UserReponse : UserEntity
    {
    }

    public class UserReponseMapperProfile : Profile
    {
        public UserReponseMapperProfile()
        {
            CreateMap<User, UserReponse>()
                .ForMember(d => d.Password, s => s.Ignore());
        }
    }
}