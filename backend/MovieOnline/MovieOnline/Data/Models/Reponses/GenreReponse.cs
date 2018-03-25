using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Reponses
{
    public class GenreReponse : Genre
    {
    }

    public class GenreReponseMapperProfile : Profile
    {
        public GenreReponseMapperProfile()
        {
            CreateMap<GenreEntity, GenreReponse>();
        }
    }
}