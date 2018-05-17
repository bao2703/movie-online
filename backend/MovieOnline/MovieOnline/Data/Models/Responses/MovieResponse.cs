using System.Collections.Generic;
using System.Linq;
using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Responses
{
    public class MovieResponse : Movie
    {
        public List<Genre> Genres { get; set; }
    }

    public class MovieResponseMapperProfile : Profile
    {
        public MovieResponseMapperProfile()
        {
            CreateMap<MovieEntity, MovieResponse>()
                .ForMember(d => d.Genres, s => s.MapFrom(m => SelectGenres(m)));
        }

        public List<Genre> SelectGenres(MovieEntity m)
        {
            var result = new List<Genre>();
            m.GenreMovies.Select(g => g.Genre).OrderBy(g => g.Name).ToList().ForEach(g => {
                result.Add(new Genre() {
                    Id = g.Id,
                    Name = g.Name,
                    Description = g.Description
                 });
            });
            return result;
        }
    }
}