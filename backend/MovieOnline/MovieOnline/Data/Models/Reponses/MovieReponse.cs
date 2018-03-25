using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Reponses
{
    public class MovieReponse : Movie
    {
    }

    public class MovieReponseMapperProfile : Profile
    {
        public MovieReponseMapperProfile()
        {
            CreateMap<MovieEntity, MovieReponse>();
        }
    }
}