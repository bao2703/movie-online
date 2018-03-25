using AutoMapper;
using MovieOnline.Data.Domains;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Reponses
{
    public class MovieReponse : MovieEntity
    {
    }

    public class MovieReponseMapperProfile : Profile
    {
        public MovieReponseMapperProfile()
        {
            CreateMap<Movie, MovieReponse>();
        }
    }
}