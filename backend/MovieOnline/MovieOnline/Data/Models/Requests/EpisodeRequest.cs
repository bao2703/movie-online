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
    public class EpisodeRequest
    {
        [Required]
        public string Name { get; set; }

        public IFormFile File { get; set; }
    }

    public class EpisodeRequestMapperProfile : Profile
    {
        public EpisodeRequestMapperProfile()
        {
            CreateMap<EpisodeRequest, EpisodeEntity>();
        }
    }
}

