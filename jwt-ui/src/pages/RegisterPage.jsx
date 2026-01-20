import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { apiService } from '../services/api';
import { toast } from 'react-toastify';

const RegisterPage = () => {
	const [name, setName] = useState('');
	const [username, setUsername] = useState('');
	const [email, setEmail] = useState('');
	const [password, setPassword] = useState('');
	const [loading, setLoading] = useState(false);
	const navigate = useNavigate();

	const handleSubmit = async (e) => {
		e.preventDefault();
		setLoading(true);
		try {
			const response = await apiService.register(name, username, email, password);
			toast.success(response.message || '가입이 완료되었습니다!');
			navigate('/login');
		} catch (err) {
			toast.error(err.message);
		} finally {
			setLoading(false);
		}
	};

	return (
		<div className='max-w-md mx-auto'>
			<div className='bg-white p-10 rounded-3xl shadow-2xl border border-gray-100'>
				<h2 className='text-2xl font-black text-gray-900 mb-8'>회원가입</h2>
				<form onSubmit={handleSubmit} className='space-y-6'>
					<div>
						<label className='block text-xs font-bold text-gray-400 mb-2 uppercase tracking-widest'>
							이름
						</label>
						<input
							type='text'
							required
							value={name}
							onChange={(e) => setName(e.target.value)}
							className='w-full px-5 py-4 rounded-2xl bg-gray-50 border-none focus:ring-2 focus:ring-blue-500 transition-all'
							placeholder='이름을 입력하세요'
						/>
					</div>
					<div>
						<label className='block text-xs font-bold text-gray-400 mb-2 uppercase tracking-widest'>
							아이디
						</label>
						<input
							type='text'
							required
							value={username}
							onChange={(e) => setUsername(e.target.value)}
							className='w-full px-5 py-4 rounded-2xl bg-gray-50 border-none focus:ring-2 focus:ring-blue-500 transition-all'
							placeholder='사용할 아이디를 입력하세요'
						/>
					</div>
					<div>
						<label className='block text-xs font-bold text-gray-400 mb-2 uppercase tracking-widest'>
							이메일
						</label>
						<input
							type='email'
							required
							value={email}
							onChange={(e) => setEmail(e.target.value)}
							className='w-full px-5 py-4 rounded-2xl bg-gray-50 border-none focus:ring-2 focus:ring-blue-500 transition-all'
							placeholder='example@mail.com'
						/>
					</div>
					<div>
						<label className='block text-xs font-bold text-gray-400 mb-2 uppercase tracking-widest'>
							비밀번호
						</label>
						<input
							type='password'
							required
							value={password}
							onChange={(e) => setPassword(e.target.value)}
							className='w-full px-5 py-4 rounded-2xl bg-gray-50 border-none focus:ring-2 focus:ring-blue-500 transition-all'
							placeholder='••••••••'
						/>
					</div>
					<button
						type='submit'
						disabled={loading}
						className={`w-full py-5 bg-blue-600 text-white font-black rounded-2xl shadow-lg transition-all ${
							loading ? 'opacity-50 cursor-wait' : 'hover:bg-blue-700 active:scale-95'
						}`}
					>
						{loading ? '처리 중...' : '가입하기'}
					</button>
				</form>
				<p className='mt-8 text-center text-gray-400 text-sm font-medium'>
					이미 계정이 있으신가요?{' '}
					<Link to='/login' className='text-blue-600 font-bold hover:underline'>
						로그인
					</Link>
				</p>
			</div>
		</div>
	);
};

export default RegisterPage;
