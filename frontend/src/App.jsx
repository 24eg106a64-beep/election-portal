import { useEffect, useState } from 'react'
import './App.css'

const fallbackCandidates = [
  {
    id: 1,
    name: 'Aisha Rahman',
    party: 'National Progressive Party',
    constituency: 'Central District',
    manifesto: 'Improving public health and digital access for every citizen.',
    imageUrl:
      'https://images.unsplash.com/photo-1494790108377-be9c29b29330?auto=format&fit=crop&w=800&q=80',
  },
  {
    id: 2,
    name: 'Daniel Lewis',
    party: "People's Unity Alliance",
    constituency: 'Northern District',
    manifesto: 'Ensuring affordable housing and better education infrastructure.',
    imageUrl:
      'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?auto=format&fit=crop&w=800&q=80',
  },
  {
    id: 3,
    name: 'Sofia Nguyen',
    party: 'Green Future Party',
    constituency: 'Eastern District',
    manifesto: 'Supporting clean energy, youth employment, and transparent governance.',
    imageUrl:
      'https://images.unsplash.com/photo-1487412720507-e7ab37603c6f?auto=format&fit=crop&w=800&q=80',
  },
]

const fallbackElections = [
  {
    id: 1,
    title: 'National Assembly Elections 2026',
    description: 'General election for constituency representatives and civic participation.',
    electionDate: '2026-11-15',
    status: 'Scheduled',
  },
]

function App() {
  const [summary, setSummary] = useState({ candidates: 3, elections: 1 })
  const [candidates, setCandidates] = useState(fallbackCandidates)
  const [elections, setElections] = useState(fallbackElections)
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const loadData = async () => {
      try {
        const [summaryResponse, candidatesResponse, electionsResponse] = await Promise.all([
          fetch('/api/dashboard'),
          fetch('/api/candidates'),
          fetch('/api/elections'),
        ])

        const summaryData = await summaryResponse.json()
        const candidateData = await candidatesResponse.json()
        const electionData = await electionsResponse.json()

        setSummary(summaryData)
        setCandidates(candidateData)
        setElections(electionData)
      } catch (error) {
        setSummary({ candidates: fallbackCandidates.length, elections: fallbackElections.length })
        setCandidates(fallbackCandidates)
        setElections(fallbackElections)
      } finally {
        setLoading(false)
      }
    }

    loadData()
  }, [])

  return (
    <div className="app-shell">
      <header className="topbar">
        <div>
          <p className="eyebrow">Election portal</p>
          <h1>Smart Election Management System</h1>
        </div>
        <button type="button" className="primary-btn">
          Admin Dashboard
        </button>
      </header>

      <section className="stats-grid">
        <article className="stat-card accent">
          <span>Total Candidates</span>
          <strong>{summary.candidates}</strong>
        </article>
        <article className="stat-card">
          <span>Active Elections</span>
          <strong>{summary.elections}</strong>
        </article>
        <article className="stat-card">
          <span>Voter Access</span>
          <strong>24/7</strong>
        </article>
      </section>

      <main className="content-grid">
        <section className="panel">
          <div className="panel-header">
            <h2>Upcoming Election</h2>
            <span className="tag">Live</span>
          </div>

          {loading ? (
            <p>Loading election details...</p>
          ) : (
            <div className="election-list">
              {elections.map((election) => (
                <div className="election-item" key={election.id}>
                  <div>
                    <h3>{election.title}</h3>
                    <p>{election.description}</p>
                  </div>
                  <div className="meta-row">
                    <span>{new Date(election.electionDate).toLocaleDateString()}</span>
                    <span className="status-pill">{election.status}</span>
                  </div>
                </div>
              ))}
            </div>
          )}
        </section>

        <aside className="panel">
          <div className="panel-header">
            <h2>Candidate Spotlight</h2>
          </div>
          <div className="candidate-list">
            {candidates.map((candidate) => (
              <article key={candidate.id} className="candidate-card">
                <img src={candidate.imageUrl} alt={candidate.name} />
                <div>
                  <h3>{candidate.name}</h3>
                  <p>{candidate.party}</p>
                  <small>{candidate.constituency}</small>
                </div>
              </article>
            ))}
          </div>
        </aside>
      </main>
    </div>
  )
}

export default App
