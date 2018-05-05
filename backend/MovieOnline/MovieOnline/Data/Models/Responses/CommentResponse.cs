using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Responses
{
    public class CommentResponse : Comment
    {
    }

    public class CommentResponseMapperProfile : Profile
    {
        public CommentResponseMapperProfile()
        {
            CreateMap<CommentEntity, CommentResponse>();
        }
    }
}