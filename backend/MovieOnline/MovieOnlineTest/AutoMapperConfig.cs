using AutoMapper;
using MovieOnline.Data.Models.Responses;
using MovieOnline.Data.Models.Requests;

namespace MovieOnlineTest
{
    public static class AutoMapperConfig
    {
        public static IMapper Instance =
            new MapperConfiguration(cfg =>
            {
                cfg.AddProfile(new AuthRequestMapperProfile());
                cfg.AddProfile(new UserResponseMapperProfile());
            }).CreateMapper();
    }
}