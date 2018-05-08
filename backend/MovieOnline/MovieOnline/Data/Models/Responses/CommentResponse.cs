using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Responses
{
    public class CommentResponse : Comment
    {
        public User User { get; set; }
    }

    public class CommentResponseMapperProfile : Profile
    {
        public CommentResponseMapperProfile()
        {
            CreateMap<UserEntity, User>();
            CreateMap<CommentEntity, CommentResponse>();
        }
    }
}