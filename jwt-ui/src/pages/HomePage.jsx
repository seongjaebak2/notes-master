import { Link } from 'react-router-dom';

const HomePage = () => {
	return (
		<div className='max-w-2xl mx-auto text-center py-12'>
			<h1 className='text-4xl font-black text-gray-900 mb-4 leading-tight'>
				스프링부트 백엔드와
				<br />
				연동되는 리액트 앱
			</h1>
			<p className='text-gray-500 mb-10 text-lg'>
				회원가입, 로그인, 그리고 보호된 페이지까지
				<br />
				기본적인 인증 시스템이 구현되어 있습니다.
			</p>
			<div className='flex flex-col sm:flex-row gap-3 justify-center items-center'>
				<Link
					to='/register'
					className='w-full sm:w-auto px-10 py-4 bg-blue-600 text-white font-bold rounded-2xl shadow-xl hover:bg-blue-700 transition-all'
				>
					무료로 계정 만들기
				</Link>
				<Link
					to='/login'
					className='w-full sm:w-auto px-10 py-4 bg-white text-gray-600 font-bold rounded-2xl border border-gray-200 hover:bg-gray-50 transition-all'
				>
					기존 계정으로 로그인
				</Link>
			</div>
		</div>
	);
};
export default HomePage;
