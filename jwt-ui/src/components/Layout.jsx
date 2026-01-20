import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../contexts/AuthContext';

export const Layout = ({ children }) => {
	const { isAuthenticated, user, logout } = useAuth();
	const navigate = useNavigate();

	const handleLogout = () => {
		logout();
		navigate('/login');
	};

	return (
		<div className='min-h-screen flex flex-col font-sans bg-gray-50'>
			<nav className='bg-white border-b border-gray-200 sticky top-0 z-40 shadow-sm'>
				<div className='max-w-7xl mx-auto px-4 flex justify-between h-16 items-center'>
					<Link to='/' className='text-xl font-black text-blue-600 tracking-tighter'>
						MY-APP
					</Link>
					<div className='flex items-center gap-6 text-sm font-medium'>
						{isAuthenticated ? (
							<>
								<span className='text-gray-600 hidden sm:inline'>
									<span className='text-blue-600 font-bold'>{user?.username}</span>님 환영합니다!
								</span>
								<Link to='/dashboard' className='hover:text-blue-600 transition-colors'>
									대시보드
								</Link>
								<button
									onClick={handleLogout}
									className='text-gray-400 hover:text-red-500 transition-colors'
								>
									로그아웃
								</button>
							</>
						) : (
							<>
								<Link to='/login' className='text-gray-600 hover:text-blue-600 transition-colors'>
									로그인
								</Link>
								<Link
									to='/register'
									className='bg-blue-600 text-white px-4 py-2 rounded-full hover:bg-blue-700 transition-all shadow-md'
								>
									시작하기
								</Link>
							</>
						)}
					</div>
				</div>
			</nav>
			<main className='grow container mx-auto px-4 py-12'>{children}</main>
			<footer className='border-t border-gray-100 py-8 text-center text-gray-400 text-xs'>
				&copy; {new Date().getFullYear()} 스프링부트 && 리액트 프로젝트
			</footer>
		</div>
	);
};
