using AutoMapper;
using MovieOnline.Data.Entities;
using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Threading.Tasks;

namespace MovieOnline.Data.Models.Requests
{
    public class CommentRequest
    {
        [Required]
        public string Id { get; set; }

        [Required]
        public string Content { get; set; }

    }

    public class CommentRequestMapperProfile : Profile
    {
        public CommentRequestMapperProfile()
        {
            CreateMap<CommentRequest, CommentEntity>();
        }
    }
}

