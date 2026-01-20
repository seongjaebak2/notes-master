import { HashRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider, useAuth } from './contexts/AuthContext';
import { Layout } from './components/Layout';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import DashboardPage from './pages/DashboardPage';
import { ToastContainer } from 'react-toastify';

// 인증이 안됬으면 로그인 페이지로 (인증 페이지 보호)
const ProtectedRoute = ({ children }) => {
	const { isAuthenticated } = useAuth();
	return isAuthenticated ? <>{children}</> : <Navigate to='/login' replace />;
};

// 인증 됬으면 대시보드로 (로그안, 가입하기)
const AuthRedirect = ({ children }) => {
	const { isAuthenticated } = useAuth();
	return isAuthenticated ? <Navigate to='/dashboard' replace /> : <>{children}</>;
};

const AppRoutes = () => {
	return (
		<Routes>
			<Route path='/' element={<HomePage />} />
			<Route
				path='/login'
				element={
					<AuthRedirect>
						<LoginPage />
					</AuthRedirect>
				}
			/>
			<Route
				path='/register'
				element={
					<AuthRedirect>
						<RegisterPage />
					</AuthRedirect>
				}
			/>
			<Route
				path='/dashboard'
				element={
					<ProtectedRoute>
						<DashboardPage />
					</ProtectedRoute>
				}
			/>
			<Route path='*' element={<Navigate to='/' replace />} />
		</Routes>
	);
};

const App = () => {
	return (
		<Router>
			<AuthProvider>
				<Layout>
					<AppRoutes />
				</Layout>
				<ToastContainer
					position='top-center'
					autoClose={3000}
					hideProgressBar={false}
					newestOnTop={false}
					closeOnClick
					rtl={false}
					pauseOnFocusLoss
					draggable
					pauseOnHover
					theme='light'
				/>
			</AuthProvider>
		</Router>
	);
};

export default App;
