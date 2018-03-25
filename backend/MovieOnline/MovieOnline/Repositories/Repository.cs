using System;
using System.Collections.Generic;
using System.Linq;
using System.Linq.Expressions;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using MovieOnline.Data;

namespace MovieOnline.Repositories
{
    public interface IRepository<TEntity> where TEntity : class
    {
        TEntity SingleOrDefault(Expression<Func<TEntity, bool>> predicate);
        Task<TEntity> SingleOrDefaultAsync(Expression<Func<TEntity, bool>> predicate);
        void Add(TEntity entity);
        Task AddAsync(TEntity entity);
        void AddRange(IEnumerable<TEntity> entities);
        Task AddRangeAsync(IEnumerable<TEntity> entities);
        void Update(TEntity entity);
        void UpdateRange(IEnumerable<TEntity> entities);
        void Remove(TEntity entity);
        void RemoveRange(IEnumerable<TEntity> entities);
        void Attach(TEntity entity);
        IEnumerable<TEntity> AsEnumerable();
        TEntity FindById(object id);
        IEnumerable<TEntity> FindBy(Expression<Func<TEntity, bool>> predicate);
        IEnumerable<TEntity> FindAll();
        IEnumerable<TEntity> FindAll(string navigationPropertyPath);
        int Count();
        bool Any(Expression<Func<TEntity, bool>> predicate);
        IEnumerable<TEntity> OrderBy<TKey>(Expression<Func<TEntity, TKey>> keySelector);
        IEnumerable<TEntity> OrderByDescending<TKey>(Expression<Func<TEntity, TKey>> keySelector);
        List<TEntity> ToList();
    }

    public abstract class Repository<TEntity> : IRepository<TEntity> where TEntity : class
    {
        protected readonly NeptuneContext Context;

        protected Repository(NeptuneContext context)
        {
            Context = context;
            DbSet = Context.Set<TEntity>();
        }

        protected DbSet<TEntity> DbSet { get; }

        public TEntity SingleOrDefault(Expression<Func<TEntity, bool>> predicate)
        {
            return DbSet.SingleOrDefault(predicate);
        }

        public Task<TEntity> SingleOrDefaultAsync(Expression<Func<TEntity, bool>> predicate)
        {
            return DbSet.SingleOrDefaultAsync(predicate);
        }

        public void Add(TEntity entity)
        {
            DbSet.Add(entity);
        }

        public async Task AddAsync(TEntity entity)
        {
            await DbSet.AddAsync(entity);
        }

        public void AddRange(IEnumerable<TEntity> entities)
        {
            DbSet.AddRange(entities);
        }

        public async Task AddRangeAsync(IEnumerable<TEntity> entities)
        {
            await DbSet.AddRangeAsync(entities);
        }

        public void Update(TEntity entity)
        {
            DbSet.Update(entity);
        }

        public void UpdateRange(IEnumerable<TEntity> entities)
        {
            DbSet.UpdateRange(entities);
        }

        public void Remove(TEntity entity)
        {
            DbSet.Remove(entity);
        }

        public void RemoveRange(IEnumerable<TEntity> entities)
        {
            DbSet.RemoveRange(entities);
        }

        public void Attach(TEntity entity)
        {
            DbSet.Attach(entity);
        }

        public IEnumerable<TEntity> AsEnumerable()
        {
            return DbSet.AsEnumerable();
        }

        public TEntity FindById(object id)
        {
            return DbSet.Find(id);
        }

        public IEnumerable<TEntity> FindBy(Expression<Func<TEntity, bool>> predicate)
        {
            return DbSet.Where(predicate);
        }

        public IEnumerable<TEntity> FindAll()
        {
            return AsEnumerable();
        }

        public IEnumerable<TEntity> FindAll(string navigationPropertyPath)
        {
            if (string.IsNullOrEmpty(navigationPropertyPath))
            {
                throw new ArgumentNullException();
            }

            return DbSet.Include(navigationPropertyPath);
        }

        public int Count()
        {
            return DbSet.Count();
        }

        public bool Any(Expression<Func<TEntity, bool>> predicate)
        {
            return DbSet.Any(predicate);
        }

        public IEnumerable<TEntity> OrderBy<TKey>(Expression<Func<TEntity, TKey>> keySelector)
        {
            return DbSet.OrderBy(keySelector);
        }

        public IEnumerable<TEntity> OrderByDescending<TKey>(Expression<Func<TEntity, TKey>> keySelector)
        {
            return DbSet.OrderByDescending(keySelector);
        }

        public List<TEntity> ToList()
        {
            return DbSet.ToList();
        }
    }
}