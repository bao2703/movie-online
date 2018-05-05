using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Responses
{
    public class MovieResponse : Movie
    {
    }

    public class MovieResponseMapperProfile : Profile
    {
        public MovieResponseMapperProfile()
        {
            CreateMap<MovieEntity, MovieResponse>();
        }
    }
}