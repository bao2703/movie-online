using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using AutoMapper;
using MovieOnline.Data.Domains;

namespace MovieOnline.Data.Dtos
{
    public class MovieDto
    {
        public int Id { get; set; }

        public string Name { get; set; }

        public DateTime Release { get; set; }

        public string Description { get; set; }

        public long Views { get; set; }

        public float Rating { get; set; }

        public string Poster { get; set; }
    }

    public class MovieDtoMapperProfile : Profile
    {
        public MovieDtoMapperProfile()
        {
            CreateMap<Movie, MovieDto>();
        }
    }
}
