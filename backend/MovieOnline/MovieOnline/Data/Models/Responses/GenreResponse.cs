using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Responses
{
    public class GenreResponse : Genre
    {
    }

    public class GenreResponseMapperProfile : Profile
    {
        public GenreResponseMapperProfile()
        {
            CreateMap<GenreEntity, GenreResponse>();
        }
    }
}