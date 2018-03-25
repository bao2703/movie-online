using AutoMapper;
using MovieOnline.Data.Domains;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Reponses
{
    public class GenreReponse : GenreEntity
    {
    }

    public class GenreReponseMapperProfile : Profile
    {
        public GenreReponseMapperProfile()
        {
            CreateMap<Genre, GenreReponse>();
        }
    }
}