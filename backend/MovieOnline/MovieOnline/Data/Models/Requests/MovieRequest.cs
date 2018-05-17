using AutoMapper;
using Microsoft.AspNetCore.Http;
using MovieOnline.Data.Entities;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MovieOnline.Data.Models.Requests
{
    public class MovieRequest
    {
        [Required]
        public string Name { get; set; }

        public string Description { get; set; }

        public IFormFile File { get; set; }

        public List<int> SelectedGenres { get; set; }
    }

    public class MovieRequestMapperProfile : Profile
    {
        public MovieRequestMapperProfile()
        {
            CreateMap<MovieRequest, MovieEntity>();
        }
    }
}

