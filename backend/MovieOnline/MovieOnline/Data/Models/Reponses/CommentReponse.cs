using AutoMapper;
using MovieOnline.Data.Bases;
using MovieOnline.Data.Entities;

namespace MovieOnline.Data.Models.Reponses
{
    public class CommentReponse : Comment
    {
    }

    public class CommentReponseMapperProfile : Profile
    {
        public CommentReponseMapperProfile()
        {
            CreateMap<CommentEntity, CommentReponse>();
        }
    }
}