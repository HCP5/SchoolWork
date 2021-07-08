using System.Collections;
using System.Collections.Generic;
using Domain;

namespace Repository
{
    public interface IObjectRepo<TE,TId> where TE : IObject<TId>
    {
        IEnumerable<TE> FindAll();
        
        TE FindOne(TId id);
        void Save(TE entity);
        void Update(TId id,TE entity);
        void Delete(TId id);
    }
}