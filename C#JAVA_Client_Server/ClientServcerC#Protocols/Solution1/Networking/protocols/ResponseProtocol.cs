using System;
using System.Collections.Generic;
using Domain;
using Networking.dtos;

namespace Networking.protocols
{
    public interface Response
    {
    }

    [Serializable]
    public class LogedInResponse : Response
    {
        public LogedInResponse()
        {
        }
    }
    
    [Serializable]
    public class UpdateResponse: Response{}

    [Serializable]
    public class NoUserFound : Response
    {
        public NoUserFound()
        {
        }
    }

    [Serializable]
    public class LogedOutResponse : Response
    {
        public LogedOutResponse()
        {
        }
    }

    [Serializable]
    public class TakeAllResponese : Response
    {
        private AllForStageDTO allEntities;

        public TakeAllResponese(AllForStageDTO allEntities)
        {
            this.allEntities = allEntities;
        }

        public AllForStageDTO AllEntities => allEntities;
    }
    [Serializable]
    public class DetailsOfRace : Response
    {
        private readonly List<Sofer> list;

        public DetailsOfRace(List<Sofer> soferiParticipanti)
        {
            this.list = soferiParticipanti;
        }

        public List<Sofer> List => list;
    }

    [Serializable]
    public class TeamDetails : Response
    {
        private readonly List<Sofer> list;

        public TeamDetails(List<Sofer> list)
        {
            this.list = list;
        }

        public List<Sofer> List => list;
    }
}