using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Responses
{
    public class EpisodeResponse : Episode
    {
    }

    public class EpisodeResponseMapperProfile : Profile
    {
        public EpisodeResponseMapperProfile()
        {
            CreateMap<EpisodeEntity, EpisodeResponse>();
        }
    }
}